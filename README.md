# Fullstack Challenge

# Context

The objective of this challenge is to create a web project to register units of a sugarcane mill on a database. We hope that by doing this challenge you will also learn a little bit about our market: digital agriculture 4.0.

# Requirements

- Create a web project on a cloud service of your choice (AWS, Heroku, etc) that allows users to register Mills (usinas), Harvests (safras), Farms (fazendas) and Fields (talhoes), and show all the fields according to a filter.

- Rules:
    1. The Mill must have a name and should be able to have multiple Harvests;
    2. The Harvest must have a code, start and end date, and should be able to have multiple Farms;
    3. Farms must have a code, name, and should be able to have multiple fields;
    4. Fields must have a code and GPS coordinates (latitude and longitude).

- You must create options to register Mills, Harvests, Farms and Fields.

- When you insert a new Mill, Harvest, Farm or Field on the database, you should send an alert to all users and show the data of the respective new entity saved on database. You must to use WEB SOCKET for this task.

- You must create a filter by Mill name, Harvest start and end date, Harvest code, Farm name and code, and Field code.

- You must show images in the map representing the fields (Use an image of your choice) according to the filter made by the user.

- You must create a simple interface with a good user experience.

- You must create unit and integrated tests.

- You must use one of this group of technologies:    

    1. NODE JS, Express JS, Sequelize JS, Postgre with Postgis, React js;
    2. Java, Spring boot, Spring data, Postgre with Postgis, React js.

- You must publish this project at https://github.com/.

- You must publish this project in a cloud of your choice AWS, HEROKU, etc) and send us a link to enter and test your project like an user.

- Send us an email before you start the challange:   

    * Email: cyan-dev@modclima.com.br;
    * Subject: FULLSTACK CHALLENGE :: Your_Name ;
    * Message: Hi, I'm starting the challenge.

# Rating
The challenge should be sent to "philipp.edson@cyan-agro.com" and "cyan-dev@modclima.com.br" with the link to the Github repository;
We will judge your service's architecture, code quality, understanding of business rules, and how prepared this service would be for deploying to a production environment.
After we analyze your challenge, we will call you to schedule a presentation of your challenge and interview with the team. We will discuss the decisions you have made also. We believe that 1 week is a good amount of time to do the challenge, but we know that not everyone has the same level of
availability. Let us know if you need more time.

# Tips
If you can, please try to implement:

- Unit and/or integration tests;
- Detailed documentation;
- CI.

Feel free to contact us if you have any doubts! If you want suggestions of which stack you should use, you can write us at cyan-dev@modclima.com.br.

Good luck!

Cyan devs
PS: Do not forget to write how we can run your code!
