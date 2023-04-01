# LostAndFound
Project that realizes work of "lost and found". In this project you can see technologies like: 
Spring Boot, Spring Data, Spring MVC, PostgreSQL (by default), Flyway migrations, Freemarker, JUnit.

After adding this project to your local repository you need:
- If you want, you can change DBMS in pom.xml and put connection settings in application.properties file in variables spring.datasource.url, spring.datasource.username, spring.datasource.password;
- After first run of the application you need to change variable spring.jpa.hibernate.ddl-auto in application.properties file on value "validate" or your database will always working only for one run;

In this project you can see realization of work "Lost and found" that finds items and saves its with all characteristics: place where item was lost, assessed value, type of this item and peculiarities that realizes relationship many-to-many with lost items.
