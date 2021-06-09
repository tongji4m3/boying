[TOC]

[typora](#页内链接) 

### 页内链接

## **[General View](#1. General View)**

\* [1.  生成目录](#1) 

* [1.1 第二级1](#1.1) 
* * [1.2 第二级2](#1.2) 
  * * [2.结语](#2) 

# 1. General View

## 1.1 Background

With the rapid economic development, more and more people begin to pay attention to spiritual and cultural life. In their spare time, they will choose to enjoy various forms of performances such as concerts, movies, and dramas. At the same time, people also demand fast and convenient service. Higher and higher, which puts forward higher requirements for the construction and management of ticket sales. The traditional off line ticket sales method has many defects that deserve improvement, such as real-time difference, long transaction time, high operating cost, non-sharing of information, etc., which can no longer meet the needs of users. In this context, we hope to build a network platform , So that users can understand the relevant content of the performance before arriving at the performance place, and realize on-line ticket booking, so as not to waste time, so as to realize the information of ticketing. 

With the rapid entertainment ticketing development of platform like Damai, Taopiaopiao and Maoyan, on-line ticketing platform seem to be the very first choice for computer audience nowadays. However, audience of shows and films still tend to buy more first-class shows, and this market is still booming with more and more audience. However, there doesn't exit a comprehensive platform for players to buy various tickets of various shows. So here comes Boying!

Boying is an on-line entertainment ticketing platform selling different shows and films even other form of shows. Our sells involves drama, crosstalk, tournament, show, concert and so on.

## 1.2 Vision

A new business of an e-commerce company will be initiated. The system to support it must manage the acquisition and selling processes of the company. Before the appearance of Boying, the usual way for audience to buy entity shows is buying them on on-line retailers like Damai, Taopiaopiao, Maoyan and so on. There are several problems about this. First, most of those entity shows sold on those websites are from personal business rather than official, which will cast doubt on the quality of the products. They could be illegal copies or second handed. Additionally, the price of the products can float because of the lack of official rules, thus chances are that audience buy a product at a unnecessary high price. Another relative safer way to buy entity shows is buying them on the official websites of the shows or the publishers. However, it annoys people that they have to create different accounts for varied websites to buy shows they want. As is demonstrated above, there is a vacancy of a comprehensive entity show market where audience with different demand can buy absolute legal copies of the entity shows they want and publishers can attract all sorts of audience to try their shows. Actually, Boying can also serve as a community besides a store. 

## 1.3 Requirement

Access of the show store for customers and management of the company must be accomplished through a Web site. The user can access it via PC. The system must allow customers to search in our system for his or her favorite show and add shows to shopping cart or wish list. audience with different show consoles can buy all sorts of physical shows easily. And what is super significant is that they need to confirm what they buy are legal copies. To ensure that, the system must maintenance a audience forum that all customers could refer to the comments from others before buying the specific show and delivery personal opinions on a product after they purchase the show. When the show is ordered, it is delivered immediately if available in stoke, or else, the specific show is ordered to ]the publisher, and a compatible deadline is informed to the customer. The system must give publishers a platform to add new shows, managing show-related information, and managing orders conveniently. The functions about the information management of shows are only belong to the publisher, ordinary users do not have these permissions. Besides, because publishers always look forward to a lower cut of the sale platform, with audience owning all sorts of show consoles and a lower middleman’s cut, Boying will provide a suitable share of the profit to fit publishers' demands. The system must allow a manager to generate reports on bestselling shows, and on most profitable customers, as well as suggest shows for buying based on past customer’s interests. Furthermore the system must have the capability of predicting the sales in order to provide better decision (inventory, reordering products, etc.) with the solid foundation. When an entity show is set to be delivered, the system should be able make a decision of selecting the most economical way provide that the deadline can be met.

## 1.4 Assumptions

1. All the tickets are the legal edition. They are from the  legal way.
2. All the orders shall be in the real name system.
3. Administrators and tickets provider can contract through e-mail.
4. Customer service have all mastered relevant knowledge and skills.
5. Clients pay for their orders with Alipay or WeChat Pay.
6. The ticket can be electronic or papery and third-party logistics company contract to deliver all the papery tickets.

# 2. Initiation and Scope Definition

## 2.1 Requirement Management

### 2.1.1 Determination and Negotiation

With the development of platforms such as Damai, Taopiaopiao and Maoyan, today, shows seem to have become the first choice for audience. However, players of other gaming machines still tend to purchase physical shows for collection or other purposes, and as more and more players purchase gaming machines, this market is still booming. 

In such a large environment, we decided to create a sales platform specifically for the market, let major show manufacturers sell their products on the platform, users can purchase, evaluate, communicate and other activities on this platform Consultation and show purchase experience.

Therefore, customers must go through the website to access the show store and manage the company. Users can access it via PC. The system must allow the customer to search his or her favorite show in our system and add the show to the shopping cart or wish list. The system must provide a platform for publishers to add new shows, manage show-related information, and easily manage orders.

## 2.2 Feasibility Analysis

### 2.2.1 Functions

Functions for users:

1. The user registers in Boying.
2. The user logs in to Boying.
3. The user views shows categories in Boying.
4. The user views show details in Boying.
5. The user views self-information in Boying.
6. The user views self-address in Boying.
7. The user adds delivery address in Boying.
8. The user purchases the show in Boying.
9. The user updates the order in Boying.
10. The user views the order related in Boying.
11. The user view the order details in Boying.

Functions for administrators

1. The administrator registers in Boying.
2. The administrator logs in Boying.
3. The administrator views customers list in Boying.
4. The administrator views customers personal information in Boying.
5. The administrator views shows list in Boying.
6. The administrator views shows' details in Boying.
7. The administrator views orders list in Boying.
8. The administrator views orders' details in Boying.
9. The administrator views report form about customers in Boying.
10. The administrator views report form about shows in Boying.
11. The administrator views report form about orders in Boying.
12. The administrator adds shows in Boying.
13. The administrator updates shows in Boying.
14. The administrator deletes shows in Boying.
15. The administrator freezes customers in Boying.
16. The administrator restore customers in Boying.
17. The administrator modifies orders in Boying.
18. The administrator deletes orders in Boying.

Functions for analysts:

1. Which shows are better sold in the second quarter than the first quarter?
2. Which categories of shows are the most profitable ones?
3. What is the average time between the order placed and shipped?
4. Is there any significant difference between shows published by different publishers in terms of profitability?

### 2.2.2 Constraints

- Customers would pay by credit card, Alipay, or WeChat Pay. All transactions should be secured.
- Access to the system will be available through a web site via PC.
- User (Customers) can discuss any shows, but Boying only provides shows from different companies for audience with different categories.

### 2.2.3 Features

1. Performance requirements:
   - In 95% of the cases, the response time in the general period does not exceed 1.5 seconds, and the peak period does not exceed 4 seconds.
   - Searching according to the specific conditions of number and name during non-peak hours, you can get the search results within 3 seconds.
   - The final estimated number of users is 10,000, the number of daily logged-in users is about 3,000, and the network bandwidth is 100 M bandwidth.
   - The system can satisfy 5,000 user requests at the same time and provide browsing functions for 10,000 concurrent users.

2. Security requirements:
   - Strict permission access control, after identity authentication, users can only access data within their permission range and can only perform operations within their permission range.
   - Different users have different identities and permissions. It is necessary to provide trusted authorization management under the premise that the user's identity is true and trustworthy, to protect data from illegal / unauthorized access and tampering, and to ensure data confidentiality and integrity.
   - Can withstand general malicious attacks from the Internet. Such as virus (including Trojan horse) attacks, password guessing attacks, hacking, etc.

3. Reliability requirements:
   - There are prompts for input and data are checked to prevent abnormal data.
   - The system is robust and should be able to deal with all kinds of abnormal conditions that occur during the operation of the system, such as: human operation errors, illegal data input, and hardware failure. The system should be able to handle it properly and avoid it properly.

4. Data confidentiality requirements:
   - Network transmission data should be encrypted. It is necessary to ensure that the data is not peeped, stolen, or tampered with during the collection, transmission, and processing. Business data needs to be encrypted during storage to ensure that it cannot be cracked.

5. Ease of use requirements:
   - 60% of users can master the use and purchase methods through the experience of other platforms and the description of the platform within 5 seconds of first seeing the platform.

6. Maintainability requirements
   - After receiving the modification request, the ordinary modification should be completed within 1 to 2 days; for the evaluation of the major demand or design modification should be completed within 1 week.
   - 90% of the bugs were modified within 1 working day, and others within 2 working days.

## 2.3 Scope

Boying's strategic goals include continuous growth and profitability, as well as increasing awareness and building a platform atmosphere. This project is based on the entity show market. It hopes to attract users' favor with comprehensive and exquisite shows, and optimize the work of managers with concise and refined operation methods. It will improve customer performance with excellent early warning and feedback, and gradually create a user on-line purchase entity shows is the preferred platform and provides long-term and stable for our buyers and partners. In order to achieve this goal, it is necessary to ensure sufficient show sources, and a certain amount of overhead is allowed in the early stage to increase the popularity of the platform and the complete show purchase experience.

Specific and direct goals are:

1. Meet business needs. Boying must be able to complete the purchase provided to users, and provide administrators with basic business functions such as income and expenditure inventory reports.

2. Improve work efficiency. Boying needs to respond to some preset scenarios and optimize the management of administrators to improve work efficiency.

3. Improve profitability. Boying needs to complete the summary of sales reports, inventory information, etc., so that administrators can adjust business strategies in time to obtain greater profits.

4. Enhance corporate brand. Boying is committed to improving the buying experience of buyers, enhancing the visibility of the platform, strengthening the construction of atmosphere, and improving profitability from the side.

# 3. Planning Management

## 3.1 Planning Activities

| Step                                                         | Activity                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 0 : Select project                                           | Boying: an entertainment ticketing platform                  |
| 1 : Identify project scope and objectives<br />    1.1 Identify objectives and measures of effectiveness<br />    1.2 Establish authority<br />    1.3 Identify stakeholders<br />    1.4 Modify objectives in the light of skate holder analysis<br />    1.5 Establish methods of communication | - 1.1: The codes are mainly based on java. The coding lines are about 100 thousands lines. The cost of total project is about 20000 yuan.<br />- 1.2: The project authority is controlled by the project steering committee, and is particularly responsible for setting, monitoring and revising the goals. At present, this work is performed by four people in our group.<br />- 1.3: Skate holders: Project staff; people outside the project in the same organization, like communication personnel; persons outside the organization game purchasers, game manufacturers.<br />- 1.4: Review and Revision<br />- 1.5: Communication Style |
| 2 : Identify project infrastructure<br />    2.1 Establish relationship between project and strategic planning<br />    2.2 Identify installation standards and procedures<br />    2.3 Identify project team organization | - 2.1: Need to decide in what order to execute these projects, need to establish a framework to accommodate the new system, such as hardware and software standards.<br />- 2.2: There should be standards for change control and configuration management; there may be provisions for quality checks at every point in the project life cycle; there should also be a measurement procedure to control the data that must be collected at each stage; the project manager should be aware of any relevant projects Planning and control standards.<br />- 2.3: The person in charge of a large project may need to control the organizational structure of the project team. While our team has very simple team structure. |
| 3 : Analysis project characteristics<br />    3.1 Objective- or product-driven<br />    3.2 Analysis other project characteristics<br />    3.3 Identify high-level project risk<br />    3.4 Take into account user requirements concerning implementation<br />    3.5 Select general life-cycle approach<br />    3.6 Review overall resource estimates | - 3.1: Mostly product-driven.<br />- 3.2: Features.<br />- 3.3: Assess the risk level of all projects, make risk prior check and focus on high-risk projects<br />- 3.4: Customers sometimes have their own regulatory requirements. Some of them in Requirement.<br />- 3.5 Scrum.<br />- 3.6 Identifying Resource Requirements for all projects, and consider the project's personnel allocation and other issues. |
| 4 : Identify project products and activities <br />    4.1 Identify and describe project products<br />    4.2 Document generic product flows<br />    4.3 Recognize product instances<br />    4.4 Produce ideal activity network<br />    4.5 Modify ideal to take into account need for stages and checkpoints | - 4.1: Identifying all the items to be created by the project helps to ensure that all activities that need to be performed have been considered. Including deliverables, intermediate products, etc., including both technical products and products related to project management and quality. These products have their own hierarchical structure, which can be represented by Product Breakdown Structure.<br />- 4.2: Determine the order in which products are created or used through the Product Flow Diagram<br />- 4.3: When the same common PFD fragment is related to multiple instances of a particular type of product, we try to identify each instance.<br />- 4.4: The ideal activity web with sufficient resources.<br />- 4.5: Introduce checkpoint activity to modify activity network. |
| 5 : Estimate effort for each activity<br />    5.1 Carry out bottom-up estimates<br />    5.2 Revise plan to create controllable activities | - 5.1: Estimate the amount of staff work required for each activity, possible time consumption, and required non-human resources with Network Plan<br />- 5.2: Activities that take a long time to split, activities that take a short time to merge. Set the time span of the activity to be the same as the reporting period used to monitor and control the project.<br /> |
| 6 : Identify out bottom-up estimates <br />    6.1 Identify and quantify activity-based risks<br />    6.2 Plan risk reduction and contingency measures where appropriate <br />    6.3 Adjust plans and estimates to take account of risks | - 6.1: Review each activity and estimate their risk of success.<br />- 6.2: Some identified risks can be avoided or at least reduced. If there is a risk, the emergency plan specifies the actions to be taken.<br />- 6.3: May change the plan, or add some new activities to reduce risk. |
| 7 : Allocate resources<br />    7.1 Identify and allocate resources<br />    7.2 Revise plans and estimates to take account of resource constraints | - 7.1: Record the type of employees required for each activity, identify the employees available for the project, and temporarily assign to these projects.<br />- 7.2: Establish priorities for tasks to ensure the completion of key tasks; ensure the full work and high utilization rate of available personnel, presented using Gantt charts. |
| 8 : Review/ publicize plan                                   | - 8.1: When each task is completed, determine whether the task can be ended by determining good quality criteria.<br/>- 8.2: Document the plan carefully so that the various departments of the project understand the plan and agree to commit to the plan. |
| 9/10 : Execute plan/ lower levels of planning<br />& May require the reiteration of lower level planning | Once the project starts, it is necessary to make a more detailed plan for each phase that is about to begin, and let go of the detailed planning for the subsequent phases. |

## 3.2 Project Organization

### 3.2.1 Team Structure

There is the organization of our development team. Each manager is responsible for his or her department, report their work and progress to the project manager. The project manager will charge the whole our and make sure the project is under control. The product manager will join in the development process to guarantee that the project meets the requirements and take charge of the later popularizing of the bookstore website.

![image-20210425163412805](https://i.loli.net/2021/04/25/g89URGIaueNWtHc.png)

### 3.2.2 Roles, Responsibilities and Authority

Every one in our team has a specific responsibility, and the following table shows each of the member’s respective responsibility:

| Roles                      | Name                                        | Responsibility                                               | Phone       |
| -------------------------- | ------------------------------------------- | ------------------------------------------------------------ | ----------- |
| Project Manager            | Jiasheng Shi                                | Take full responsibility for the entire project, monitor development progress, make decision on risk control and resource provision, and ensure software quality. | 19946254167 |
| Requirements Analyst       | Cheng Fu                                    | Responsible for communicating requirements with customers, assisting project manager to control and follow up requirement change. | 19921311127 |
| Product Architect          | Cuiqi Li                                    | Responsible for the design of the software part of the system structure and model, develop the software development plan, determine the software technology selection. | 15316162191 |
| Product Design Manager     | Cheng Fu                                    | Responsible for monitoring project functional requirements and product design, as well as product functional design and interaction design. | 19921311127 |
| Product Designer           | Jiasheng Shi、Cheng Fu、Jiarui Li、Cuiqi Li | Responsible for the collection and analysis of needs, product design and interaction design. | ——          |
| User Interface Designer    | Jiarui Li                                   | Responsible for prototype design and user experience design. | 13984086392 |
| Technical Manager          | Cuiqi Li                                    | Responsible for system function module coding implementation and correction test feedback product defects. | 15316162191 |
| Team Leader                | Jiasheng Shi                                | Responsible for management of the development team and monitoring the progress of the project. | 19946254167 |
| Development Engineer       | Jiasheng Shi、Cheng Fu、Jiarui Li、Cuiqi Li | Responsible for system function module coding implementation and correction test feedback product defects. | ——          |
| Quality Assurance Manager  | Jiasheng Shi                                | Responsible for test plan, and the whole quality assurance activities of the project. | 19946254167 |
| Quality Assurance          | Jiasheng Shi、Cheng Fu、Jiarui Li、Cuiqi Li | Responsible for test cases design, test execution and evaluation of the test execution process, as well as evaluate test results and document defects found. | ——          |
| System Administrator       | Jiarui Li                                   | Responsible for deployment of software products, completion of project related system engineering work, and customer technical support. | 13984086392 |
| Software Quality Assurance | Cheng Fu                                    | Responsible for supervising the process planning and implementation of the project, checking the products produced by the project, and checking the conformity of the project development process. | 19921311127 |
| Database Administrator     | Jiarui Li                                   | Responsible for designing and constructing database system and optimizing database performance. | 13984086392 |

### 3.2.3 Communication Style

Communicate in the team through the following collaborative communication methods:

1. Formal, non-personal methods such as software engineering documents and project products, memos, schedules and project control tools, change requests, etc .;

2. Formal, person-to-person communication, focusing on quality assurance, such as status review meetings, design, and code inspection;

3. Informal, person-to-person communication, such as group meetings to exchange information and solve problems;

4. E-mail, mainly to communicate with people outside the project such as instructors and certain technical personnel.

## 3.3 Software Project Planning

### 3.3.1 WBS

According to the software engineering methodology, we divide the overall system development into six main processes: requirements analysis, outline design, detailed design, coding, testing, and deployment, and then decompose the six major processes respectively.

For demand analysis, it is mainly divided into four parts: demand collection, demand communication, demand analysis and demand confirmation.

In the outline design stage, we mainly complete the interface design, interface design, architecture design, database design and other contents.

The detailed design stage is subdivided into module design, class design, data flow design, interaction design, and navigation design.

The coding is split into front-end coding, back-end coding and database implementation.

Testing is divided into unit testing, integration testing, system testing and acceptance testing based on software testing theory.

Finally, we configure the server environment for the deployment phase, project deployment and user documentation.

![image-20210425104905149](https://i.loli.net/2021/04/25/5jkwtRBphK3DYdr.png)

### 3.3.2 Network Plan & Critical Path

The project uses an activity-based approach to identify activities, divides the project into the main life cycle stages, considers the activities of each stage and its activity cycle separately, and analyzes the pre activities of individual activities to obtain the following activity list :

| No   | Activity                      | Estimated Duration (Weeks) | Depends on |
| ---- | ----------------------------- | -------------------------- | ---------- |
| A    | Requirement Analysis          | 3                          | None       |
| B    | User-Interface Design         | 2                          | A          |
| C    | Data Design                   | 3                          | A          |
| D    | Architecture Design           | 4                          | A          |
| E    | Interface Design              | 3                          | A          |
| F    | Design Review                 | 1                          | B,C,D,E    |
| G    | Front-end Coding              | 3                          | F          |
| H    | Back-end Coding               | 4                          | F          |
| I    | Software Testing              | 2                          | G,H        |
| J    | User Document                 | 1                          | F          |
| K    | Deployment and System Testing | 1                          | I          |
| L    | The quality of statistical    | 1                          | K          |
| M    | User Training                 | 1                          | L          |

**Network plan** is like below, with the yellow background highlights the **Critical Path** :

![微信图片_20210425190614](https://i.loli.net/2021/04/25/2UdVHlCoJE7MyLw.png)

Relative Gantt chart:

