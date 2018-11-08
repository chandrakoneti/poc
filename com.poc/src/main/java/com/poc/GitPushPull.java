package com.poc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitPushPull {

	public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, GitAPIException {

		GitPushPull gitPushPull = new GitPushPull();
		String str = "Chandra";
		gitPushPull.pushRemote(str);
	}

	private void pushRemote(String str)
			throws IOException, InvalidRemoteException, TransportException, GitAPIException {
		File localPath = File.createTempFile("GitRepo", "");
		if (!localPath.delete()) {
			throw new IOException("Could not delete repo " + localPath);
		}
		String REMOTE_URL = "https://github.com/chandrakoneti/poc.git";
		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
		Git git = Git.cloneRepository()
				.setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out)))
				.setURI(REMOTE_URL)
				.setDirectory(localPath)
				.setCredentialsProvider(new UsernamePasswordCredentialsProvider("chandra.koneti@gmail.com", "Chandra@git2018"))
				.call();
		Repository repository = git.getRepository();
		System.out.println("Repos"+ repository);
	}

}
