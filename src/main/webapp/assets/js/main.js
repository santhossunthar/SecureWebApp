var profileIcon = document.getElementById("profile-icon");
var navItemsList = document.getElementById("nav-items-list");

profileIcon.addEventListener("click", function() {
    if (navItemsList.style.display === "block") {
        navItemsList.style.display = "none";
    } else {
        navItemsList.style.display = "block";
    }
});

window.addEventListener("click", function(event) {
    if (!event.target.matches('#profile-icon')) {
        if (navItemsList.style.display === "block") {
            navItemsList.style.display = "none";
        }
    }
});