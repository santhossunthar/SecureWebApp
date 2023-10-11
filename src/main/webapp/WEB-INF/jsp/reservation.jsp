<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                VRS
            </div>

            <div class="nav-items">
                <ul class="nav-items-list">
                    <a href="/dashboard">
                        <li class="list-item">Dashboard</li>
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
                <form method="post" action="/reservation">
                    <label for="date">Date</label>
                    <input type="date" name="date"/>
                    <label for="time">Preferred time</label>
                    <select name="time" id="time">
                        <option value="10">10 AM</option>
                        <option value="11">11 AM</option>
                        <option value="12">12 PM</option>
                    </select>
                    <label for="location">Preferred Location</label>
                    <input type="text" name="location"/>
                    <label for="vehicleno">Vehicle Registration Number</label>
                    <input type="text" name="vehicleno"/>
                    <label for="mileage">Current Mileage</label>
                    <input type="text" name="mileage"/>
                    <label for="message">Message</label>
                    <input type="text" name="message"/>
                    <button type="submit">Reserve</button>
                    <button type="reset">Clear</button>
                </form>
                <% String message = (String) request.getAttribute("response");
                System.out.println(message);
                if(message != null){ %>
                    <p><%= message %></p>
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