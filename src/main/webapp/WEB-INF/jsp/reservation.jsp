<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.time.LocalTime" %>
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
                         List<HashMap<String, Object>> reservationsList = (List<HashMap<String, Object>>) request.getAttribute("reservationsDetails");
                         for (HashMap<String, Object> reservation : reservationsList) {
                       %>
                       <tr>
                           <td><%= reservation.get("bookingId").toString() %></td>
                           <td><%= reservation.get("date").toString().replace("-", "/") %></td>
                           <td><%= reservation.get("time").toString() %></td>
                           <td><%= reservation.get("location").toString() %></td>
                           <td class="actions">
                               <form action="/reservation/view" method="POST">
                                  <input type="hidden" name="bid" value="<%= (int) reservation.get("bookingId") %>"/>
                                  <input type="hidden" name="token" value="<%= (String) request.getAttribute("csrfToken") %>"/>
                                  <button class="btn action-view" type="submit">View</button>
                               </form>

                               <form action="/reservation/delete" method="POST">
                                 <input type="hidden" name="bid" value="<%= (int) reservation.get("bookingId") %>"/>
                                 <input type="hidden" name="token" value="<%= (String) request.getAttribute("csrfToken") %>"/>
                                 <button class="btn action-delete" type="submit">Delete</button>
                               </form>
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

</body>

</html>