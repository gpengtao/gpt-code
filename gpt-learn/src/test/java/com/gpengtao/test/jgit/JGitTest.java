package com.gpengtao.test.jgit;

import lombok.SneakyThrows;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * @author pengtao.geng on 2023/11/14 20:41.
 */
public class JGitTest {

	File gitFile = new File("./target/gitTemp");

	UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("pengtao.geng", new String(Base64.getDecoder().decode("R2VuZzExMTEu")));

	@SneakyThrows
	@Test
	public void test_clone() {
		if (gitFile.exists()) {
			FileUtils.delete(gitFile, FileUtils.RECURSIVE);
		}

		// clone 仓库到指定目录
		Git git = Git.cloneRepository()
				.setURI("https://git.corp.bianlifeng.com/smart-order/smartorder-strategy-engine.git")
				.setDirectory(gitFile)
				.setCredentialsProvider(credentialsProvider)
				.call();

		// pull
		git.pull().setCredentialsProvider(credentialsProvider).call();

		git.close();
	}

	@SneakyThrows
	@Test
	public void test_master_to_tag() {
		// git切到master
		Git git = Git.open(gitFile);
		git.pull().setCredentialsProvider(credentialsProvider).call();
		git.checkout().setName("master").call();

		// 最后一个commit
		Iterable<RevCommit> log = git.log().call();
		ObjectId targetObjectId = log.iterator().next().getId();
		System.out.println("最后一个commit: " + targetObjectId.getName());

		// 找这个commit对应的tag
		List<Ref> allTagRefs = git.tagList().call();
		allTagRefs.stream()
				.filter(ref -> Objects.equals(ref.getPeeledObjectId().toObjectId(), targetObjectId))
				.forEach(ref -> System.out.println(ref.getName()));
	}

	@SneakyThrows
	@Test
	public void test_file() {
		File file = new File("./target/11");
		System.out.println(file.exists());
		System.out.println(file.isDirectory());

		String tempDir = System.getenv("java.io.tmpdir");
		System.out.println(tempDir);
		File temp = File.createTempFile("aaa", ".temp", new File("./target"));
		System.out.println(temp);
	}
}
