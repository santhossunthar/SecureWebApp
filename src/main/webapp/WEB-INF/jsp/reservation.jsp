<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Dashboard</title>
</head>

<body>
    <nav>
        <div class="container">
            <div class="brand-logo">
                VRS
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

    <section id="user-dashboard">
        <div class="container">
            <div class="reservations">
                <div class="header">
                    <h1>Reservations</h1>
                    <a href="/reservation/add"><button class="btn">ADD</button></a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                       <%
                         List<HashMap<String, Object>> productList = (List<HashMap<String, Object>>) request.getAttribute("reservationsDetails");
                         for (HashMap<String, Object> product : productList) {
                       %>
                       <tr>
                           <td><%= product.get("bookingId") %></td>
                           <td><%= product.get("date") %></td>
                           <td><%= product.get("time") %></td>
                           <td><%= product.get("location") %></td>
                           <td class="actions">
                               <a href="/reservation/view?bid=<%= product.get("bookingId") %>">
                                    <button class="btn action-view">View</button>
                               </a>
                               <a href="/reservation/delete?bid=<%= product.get("bookingId") %>">
                                    <button class="btn action-delete">Delete</button>
                               </a>
                            </td>
                        </tr>
                       <%
                         }
                       %>
                    </tbody>
                </table>
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