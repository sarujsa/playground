package sarujsa.docker.csm.client.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import sarujsa.docker.csm.client.exceptions.LocationNotFoundException;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.dto.LocationDto;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("csmClientPrompt")
public class Prompt {

  private final RequestGenerator requestGenerator;
  private final Map<String, LocationDto> locationMap;

  private static final String EXIT = "exit";
  private final Pattern postPattern;
  private final Pattern getPattern;
  private final PrintWriter printWriter;

  public Prompt(
      RequestGenerator requestGenerator,
      @Qualifier("locationMap") Map<String, LocationDto> locationMap) {
    this.locationMap = locationMap;
    this.printWriter = new PrintWriter(System.out, true);
    this.requestGenerator = requestGenerator;
    this.postPattern = Pattern.compile("post\\s+([\\w\\s]+)//([\\w\\s]+)//([\\w\\s]+)");
    this.getPattern = Pattern.compile("get\\s+([\\w\\s]+)");
  }

  public void beginLoop() {
    printGreetings();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      printWriter.print("> ");
      printWriter.flush();
      String line = scanner.nextLine();
      if (EXIT.equals(line.trim())) {
        break;
      }
      PromptType promptType = executePromptIfValid(line);
      if (promptType == PromptType.BAD_FORM) {
        printInstructions();
      }
    }
    printExitMessage();
  }

  private enum PromptType {
    SUCCESSFUL,
    BAD_FORM
  }

  private PromptType executePromptIfValid(String line) {
    Matcher matcher = postPattern.matcher(line);
    if (matcher.matches()) {
      handlePostRequest(matcher);
      return PromptType.SUCCESSFUL;
    }
    matcher = getPattern.matcher(line);
    if (matcher.matches()) {
      handleGetRequest(matcher);
      return PromptType.SUCCESSFUL;
    }
    return PromptType.BAD_FORM;
  }

  private void handleGetRequest(Matcher matcher) {
    try {
      ResponseEntity<AttractionDto> response = requestGenerator.generateGetRequst(matcher);
      printWriter.println("Response was: " + response);
    } catch (LocationNotFoundException e) {
      printAvailableLocations();
    }
  }

  private void handlePostRequest(Matcher matcher) {
    try {
      ResponseEntity<?> response = requestGenerator.generatePostRequst(matcher);
      printWriter.println("Response was: " + response);
    } catch (LocationNotFoundException e) {
      printAvailableLocations();
    }
  }

  private void printGreetings() {
    printWriter.println(
        "Welcome to CSM client. The client makes http requests towards the csm server.");
    printInstructions();
    printAvailableLocations();
  }

  private void printAvailableLocations() {
    printWriter.println("Available locations are:" + locationMap.keySet().stream().toList());
  }

  private void printInstructions() {
    printWriter.println("Available commands are: post, get, exit");
    printWriter.println(
        "The format of post command is: post attraction name // location name // attraction description");
    printWriter.println("The format of get command is: get attraction name");
    printWriter.println("To shutdown the client, just input 'exit' (without '')");
  }

  private void printExitMessage() {
    printWriter.println("Shutting down the client...");
  }
}
