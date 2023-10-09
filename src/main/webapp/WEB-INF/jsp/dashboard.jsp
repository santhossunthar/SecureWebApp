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
                <div id="profile-icon">
                    <img src="/assets/images/user.png" alt="user-icon" width="20px" height="20px">
                </div>
            </div>
        </div>

        <div id="nav-items-list">
            <ul>
                <li><a href="/dashboard">Dashboard</a></li>
                <li><a href="/profile">Profile</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <section id="user-dashboard">
        <div class="container">
            <div class="reservations">
                <div class="header">
                    <h1>Reservations</h1>
                    <a href="/reservation"><button>ADD</button></a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Location</th>
                            <th>Vehicle No</th>
                            <th>Mileage</th>
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
                                    <td><%= product.get("vehicleNo") %></td>
                                    <td><%= product.get("mileage") %></td>
                                    <td class="actions">
                                        <button class="btn-action-view">View</button>
                                        <button class="btn-action-delete">Delete</button>
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