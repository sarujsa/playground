Spring Integration Demo Application
===================================

A simple serverless Spring application made as a POC practice of Spring Integration with annotation-based config.

The app polls an **input** directory for a file, then transfers that file throuh
the Spring Integation pipeline - logging the file into a wire tap, then copying the file into the **out** directory.