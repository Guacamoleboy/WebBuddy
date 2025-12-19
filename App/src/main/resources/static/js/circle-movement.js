// Gets our class name
const hero = document.querySelector('.hero');

// Circle Setup
let circles = [
    { x: 30, y: 70, targetX: 30, targetY: 70, speed: 0.01 + Math.random() * 0.005 },
    { x: 70, y: 30, targetX: 70, targetY: 30, speed: 0.01 + Math.random() * 0.005 }
];

// Next circle target
function setNewTarget(circle) {
    circle.targetX = 10 + Math.random() * 80;
    circle.targetY = 10 + Math.random() * 80;
}

// Animation function | DO NOT ADJUST WITHOUT @GUACAMOLEBOY
function animateCircles() {
    circles.forEach(circle => {

        // Slowly towards next target
        circle.x += (circle.targetX - circle.x) * circle.speed;
        circle.y += (circle.targetY - circle.y) * circle.speed;

        // Removes target break (standing in end point for too long)
        if (Math.abs(circle.x - circle.targetX) < 0.5 && Math.abs(circle.y - circle.targetY) < 0.5) {
            setNewTarget(circle);
        }
    });

    // Update position dynamicly
    hero.style.setProperty('--circle1-pos', `${circles[0].x}% ${circles[0].y}%`);
    hero.style.setProperty('--circle2-pos', `${circles[1].x}% ${circles[1].y}%`);

requestAnimationFrame(animateCircles);

}

animateCircles();