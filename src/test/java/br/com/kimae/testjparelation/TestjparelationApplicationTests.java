package br.com.kimae.testjparelation;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class TestjparelationApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(TestjparelationApplicationTests.class);

	@Autowired
	private RootRepository rootRepository;

	@Autowired
	private LeafRepository leafRepository;

	@Test
	@Sql("/create.sql")
	void mustRemoveOldChildrenAndKeepOnlyNewOnes() {
		Leaf leaf4 = new Leaf();
		leaf4.setRootId(1);
		leaf4.setName("leaf 4");
		Leaf leaf5 = new Leaf();
		leaf5.setRootId(1);
		leaf5.setName("leaf 5");

		Root root = new Root();
		root.setId(1);
		root.setLeafs(Arrays.asList(leaf4, leaf5));

		rootRepository.saveAndFlush(root);
		leafRepository.flush();

		List<Leaf> leafsAfterSave = leafRepository.findAll();
		logger.info(leafsAfterSave.toString());
		assertThat(leafsAfterSave, hasSize(2));

		Root rootAttached = rootRepository.findById(1).orElse(null);
		logger.info(rootAttached.toString());
		assertThat(rootAttached.getLeafs(),  allOf(hasSize(2)));

	}

}
