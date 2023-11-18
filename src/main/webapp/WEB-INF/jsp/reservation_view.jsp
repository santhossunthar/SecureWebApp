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
                <div class="header">
                    <h2>Reservation Details</h2>

                    <div class="btn-group">
                        <a href="/reservation/add"><button class="btn">Reserve</button></a>
                        <a href="/reservation/delete"><button class="btn action-delete">Delete</button></a>
                    </div>
                </div>
                <div class="reservation-info">
                    <h4 class="element title">Booking Id</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Date</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Time</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Location</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Vechile No</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Mileage</h4>
                    <h4 class="element">test</h4>
                    <h4 class="element">Message</h4>
                    <h4 class="element">test</h4>
                </div>
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