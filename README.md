# TicketShop

## Description
TicketShop is an online ticket shop designed to provide a platform for buying and selling tickets for manifestations. The system allows users to reserve, buy, and cancel tickets, as well as earn reward points. It also provides a search feature to find nearby and wanted manifestations.

## Key Features
- **User Management**: Allows users to create accounts, log in, and manage their profiles.
- **Manifestation Management**: Administrators can create, edit, and manage manifestations (events) including setting ticket prices, availability, and other details.
- **Ticket Types**: Supports different types of tickets (regular, fan_pit, VIP) with varying prices and availability.
- **Reward Points System**: Users can earn points for purchasing tickets, which can be redeemed for discounts or other benefits.
- **Search and Filter**: Users can search for manifestations by name, location, or date, and filter results by price, availability, and other criteria.
- **Reservation and Cancellation**: Users can reserve tickets and cancel reservations or purchased tickets.
- **Payment Gateway Integration**: Designed to integrate with payment gateways for secure and efficient payment processing.
- **Ticket Status Management**: Manages the status of tickets (reserved, purchased, cancelled) and updates availability accordingly.
- **User Notifications**: Sends notifications to users about their ticket reservations, purchases, and cancellations.
- **Admin Dashboard**: Provides insights into ticket sales, revenue, and other key metrics.

## Endpoints
The following endpoints are available in the TicketShop API:

### Ticket Endpoints
- `POST /createTicket`: Create a new ticket
- `POST /reserveTickets`: Reserve tickets for a manifestation
- `POST /buyReservedTickets`: Buy reserved tickets
- `GET /getTicket`: Get tickets
- `GET /getAllTickets`: Get all tickets

### Manifestation Endpoints
- `POST /manifestations/{manifestationId}/reserveTickets`: Reserve tickets for a specific manifestation
- `POST /manifestations/{manifestationId}/buyReservedTickets`: Buy reserved tickets for a specific manifestation
- `GET /manifestations/{manifestationId}/getTickets`: Get tickets for a specific manifestation

### User Endpoints
- `GET /users/{userId}/getReservedTickets`: Get reserved tickets for a specific user
- `GET /users/{userId}/getPurchasedTickets`: Get purchased tickets for a specific user

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.2.0 or higher
- GitLab account (for collaboration and issue tracking)

### Installation
1. Clone the repository:  
   `git clone https://gitlab.com/Rudy5ar/ticketshop.git`
2. Change into the project directory:  
   `cd ticketshop`
3. Build the project using Maven:  
   `mvn clean package`

### Running the Application
Start the application using the Maven wrapper:  
`mvnw spring-boot:run`

Access the application at:  
`http://localhost:8080`

## Contributing
We welcome contributions to TicketShop! If you're interested in contributing, please follow these steps:

1. Fork the repository:  
   `git fork https://gitlab.com/Rudy5ar/ticketshop.git`
2. Create a new branch for your feature or bug fix:  
   `git checkout -b my-feature`
3. Make your changes and commit them:  
   `git commit -m "My feature or bug fix"`
4. Push your changes to your fork:  
   `git push origin my-feature`
5. Create a merge request:  
   `git merge-request`
