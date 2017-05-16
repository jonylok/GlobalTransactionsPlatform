package global.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GlobalTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalTransactionsApplication.class, args);
	}
}
