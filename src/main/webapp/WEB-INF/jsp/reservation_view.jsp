<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Reservation</title>
</head>

<body>
    <nav>
        <div class="container">
            <div class="brand-logo">
                VSR
            </div>

            <div class="nav-items">
                <ul class="nav-items-list">
                    <a href="/reservation">
                        <li class="list-item">Reservation</li>
                    </a>
                    <a href="/profile">
                        <li class="list-item">Profile</li>
                    </a>
                    <a href="/logout">
                        <li class="list-item">Logout</li>
                    </a>
                </ul>
            </div>
        </div>
    </nav>

    <section id="user-reservation">
        <div class="container">
            <div class="service-form">
                <%
                   List<HashMap<String, Object>> productList = (List<HashMap<String, Object>>) request.getAttribute("reservationsDetails");
                   for (HashMap<String, Object> product : productList) {
                %>
                <div class="header">
                    <h2>Reservation Details</h2>

                    <div class="btn-group">
                        <a href="/reservation/add"><button class="btn">Reserve</button></a>

                        <form action="/reservation/delete" method="POST">
                             <input type="hidden" name="bid" value="<%= (int) product.get("bookingId") %>"/>
                             <input type="hidden" name="token" value="<%= (String) request.getAttribute("csrfToken") %>"/>
                             <button class="btn action-delete" type="submit">Delete</button>
                        </form>
                    </div>
                </div>
                <div class="reservation-info">
                    <h4 class="element title">Booking Id</h4>
                    <h4 class="element"><%= product.get("bookingId") %></h4>
                    <h4 class="element">Date</h4>
                    <h4 class="element"><%= product.get("date") %></h4>
                    <h4 class="element">Time</h4>
                    <h4 class="element"><%= product.get("time") %></h4>
                    <h4 class="element">Location</h4>
                    <h4 class="element"><%= product.get("location") %></h4>
                    <h4 class="element">Vechile No</h4>
                    <h4 class="element"><%= product.get("vehicleNo") %></h4>
                    <h4 class="element">Mileage</h4>
                    <h4 class="element"><%= product.get("mileage") %></h4>
                    <h4 class="element">Message</h4>
                    <h4 class="element"><%= product.get("message") %></h4>
                </div>
                <% } %>
            </div>
        </div>
    </section>

    <footer>
        <div class="content">
            2023 Copyright
        </div>
    </footer>

    <script src="/assets/js/main.js"></script>
</body>

</html>