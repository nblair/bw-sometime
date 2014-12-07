## Bedework Sometime Database

This module is responsible for maintaining the scripts to create and modify the tables used internally by Bedework Sometime.

The DDL statements are stored in the resources directory under "db/migration." 

### Running Manually

The web application will run the migrations if necessary. If you need to run these migrations manually, do the following:

1. Create a file named flyway.properties modeled after flyway-SAMPLE.properties, edit to match the target environment.
2. Run the following command:
> mvn compile flyway:migrate

