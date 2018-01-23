package com.jsanz.portfolio.server.repository;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsanz.portfolio.domain.Asset;
import com.jsanz.portfolio.domain.AssetTypeEnum;
import com.jsanz.portfolio.domain.Transaction;
import com.jsanz.portfolio.domain.TransactionTypeEnum;
import com.jsanz.portfolio.server.repository.TransactionRepository;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryIT {

	private static final String LOCALHOST = "127.0.0.1";
	private static final String DB_NAME = "itest";
	private static final int MONGO_TEST_PORT = 27028;

	private static MongodExecutable mongoExecutable;
	private static MongodProcess mongoProcess;
	private static MongoClient mongo;

	@Autowired
	TransactionRepository repository;

	@Autowired
	MongoOperations operations;

	private Transaction t1_1, t1_2, t2_1, t2_2, t2_3, t3_1;

	private List<Transaction> all;

	@BeforeClass
	public static void initializeDB() throws IOException {
		MongodStarter starter = MongodStarter.getDefaultInstance();

		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(LOCALHOST, MONGO_TEST_PORT, Network.localhostIsIPv6())).build();

		mongoExecutable = starter.prepare(mongodConfig);
		mongoProcess = mongoExecutable.start();

		mongo = new MongoClient(LOCALHOST, MONGO_TEST_PORT);
		mongo.getDatabase(DB_NAME);
	}

	@AfterClass
	public static void shutdownDB() throws InterruptedException {
		mongo.close();
		mongoProcess.stop();
		mongoExecutable.stop();
	}

	@Before
	public void setUp() throws InterruptedException {

		repository.deleteAll();

		Asset a1 = new Asset("a1", AssetTypeEnum.FUND, "a1", Currency.getInstance("EUR"));
		t1_1 = new Transaction(UUID.randomUUID().toString(), a1, TransactionTypeEnum.B, 5.45, 41.33587,
				DateTime.now().minusDays(50));
		t1_2 = new Transaction(UUID.randomUUID().toString(), a1, TransactionTypeEnum.S, 3.1447, 40.87,
				DateTime.now().minusDays(49));

		Asset a2 = new Asset("a2", AssetTypeEnum.FUND, "a2", Currency.getInstance("USD"));
		t2_1 = new Transaction(UUID.randomUUID().toString(), a2, TransactionTypeEnum.B, 6.0, 112.58,
				DateTime.now().minusDays(48));
		t2_2 = new Transaction(UUID.randomUUID().toString(), a2, TransactionTypeEnum.B, 7.0, 110.45,
				DateTime.now().minusDays(47));
		t2_3 = new Transaction(UUID.randomUUID().toString(), a2, TransactionTypeEnum.S, 12.0, 121.71,
				DateTime.now().minusDays(46));

		Asset a3 = new Asset("a3", AssetTypeEnum.FUND, "a3", Currency.getInstance("EUR"));
		t3_1 = new Transaction(UUID.randomUUID().toString(), a3, TransactionTypeEnum.B, 45.52, 15.1248,
				DateTime.now().minusDays(45));

		all = repository.saveAll(Arrays.asList(t1_1, t1_2, t2_1, t2_2, t2_3, t3_1));
	}

	@Test
	public void findsTransactionById() throws Exception {
		assertThat(repository.findById(t1_1.getId().toString()), is(Optional.of(t1_1)));
	}

	@Test
	public void findsAllTransactions() throws Exception {
		List<Transaction> result = repository.findAll();
		assertThat(result.size(), is(all.size()));
		assertThat(result.containsAll(all), is(true));
	}

	@Test
	public void findsAllTransactionWithGivenIds() {

		Iterable<Transaction> result = repository.findAllById(Arrays.asList(t1_1.getId(), t2_1.getId()));
		assertThat(result, hasItems(t1_1, t2_1));
		assertThat(result, not(hasItems(t1_2, t2_2, t2_3, t3_1)));
	}

	@Test
	public void deletesTransactionCorrectly() throws Exception {

		repository.delete(t1_1);

		List<Transaction> result = repository.findAll();

		assertThat(result.size(), is(all.size() - 1));
		assertThat(result, not(hasItem(t1_1)));
	}

	@Test
	public void deletesTransactionByIdCorrectly() {

		repository.deleteById(t1_1.getId());

		List<Transaction> result = repository.findAll();

		assertThat(result.size(), is(all.size() - 1));
		assertThat(result, not(hasItem(t1_1)));
	}

	@Test
	public void findsPagedTransactions() throws Exception {

		Page<Transaction> result = repository.findAll(PageRequest.of(1, 2, Direction.DESC, "asset.name", "date"));
		assertThat(result.isFirst(), is(false));
		assertThat(result.isLast(), is(false));
		assertThat(result, hasItems(t2_2, t2_1));
	}

	@Test
	public void existsWorksCorrectly() {
		assertThat(repository.existsById(t1_1.getId()), is(true));
	}

	@Test
	public void findsTransactionsByAssetName() throws Exception {

		List<Transaction> result = repository.findByAssetName("a2");
		assertThat(result.size(), is(3));
		assertThat(result, hasItems(t2_1, t2_2, t2_3));
	}

}
