package ca.footeware.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class Config extends AbstractCouchbaseConfiguration {

	@Override
	public String getConnectionString() {
		return "couchbase://127.0.0.1";
	}

	@Override
	public String getUserName() {
		return "craig";
	}

	@Override
	public String getPassword() {
		return "chocolate";
	}

	@Override
	public String getBucketName() {
		return "petclinic";
	}
}