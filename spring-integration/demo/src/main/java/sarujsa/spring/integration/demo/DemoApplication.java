package sarujsa.spring.integration.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@SpringBootApplication
public class DemoApplication {

  @Value("${in.file.dir.path}")
  private String inDirectoryName;

  @Value("${out.file.dir.path}")
  private String outDirectoryName;

  public static void main(String[] args) {
    var context =
        new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.NONE).run();
  }

  @Bean
  public MessageChannel fileInputChannel() {
    return new DirectChannel();
  }

  @Bean
  @InboundChannelAdapter(channel = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
  public MessageSource<File> fileInbound() {
    FileReadingMessageSource source = new FileReadingMessageSource();
    CompositeFileListFilter<File> compositeFileListFilter = new CompositeFileListFilter<>();
    compositeFileListFilter.addFilter(new SimplePatternFileListFilter("*.txt"));
    compositeFileListFilter.addFilter(new AcceptOnceFileListFilter<>());
    source.setFilter(compositeFileListFilter);
    source.setDirectory(new File(inDirectoryName));
    return source;
  }

  @Bean
  @ServiceActivator(inputChannel = "fileInputChannel")
  public FileWritingMessageHandler fileOutbound() {
    FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(outDirectoryName));
    handler.setExpectReply(false);
    handler.setDeleteSourceFiles(true);
    handler.setAutoCreateDirectory(true);
    return handler;
  }

}
