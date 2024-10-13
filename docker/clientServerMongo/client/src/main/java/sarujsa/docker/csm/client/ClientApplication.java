package sarujsa.docker.csm.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sarujsa.docker.csm.client.core.Prompt;

@SpringBootApplication
public class ClientApplication {

  public static void main(String[] args) throws InterruptedException {
    var context =
        new SpringApplicationBuilder(ClientApplication.class).web(WebApplicationType.NONE).run();

    Prompt prompt = context.getBean(Prompt.class);
    prompt.beginLoop();
  }

}
