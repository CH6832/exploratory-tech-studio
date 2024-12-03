// Ensure you have included Chart.js library in your project
// Add this in your HTML: <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

document.addEventListener("DOMContentLoaded", () => {
  // Pie Chart (Status Distribution)
  const pieCtx = document.getElementById("pieChart").getContext("2d");
  new Chart(pieCtx, {
    type: "pie",
    data: {
      labels: ["Approved", "Pending", "Rejected"],
      datasets: [
        {
          data: [30, 15, 5], // Example data
          backgroundColor: ["#4caf50", "#ffc107", "#f44336"],
        },
      ],
    },
    options: {
      plugins: {
        legend: {
          display: true,
          position: "bottom",
        },
      },
    },
  });

  // Bar Chart (Monthly Trends)
  const barCtx = document.getElementById("barChart").getContext("2d");
  new Chart(barCtx, {
    type: "bar",
    data: {
      labels: ["January", "February", "March", "April", "May"], // Example months
      datasets: [
        {
          label: "Reports Created",
          data: [5, 10, 15, 20, 25], // Example data
          backgroundColor: "#4caf50",
        },
      ],
    },
    options: {
      scales: {
        x: {
          title: {
            display: true,
            text: "Months",
          },
        },
        y: {
          title: {
            display: true,
            text: "Number of Reports",
          },
          beginAtZero: true,
        },
      },
    },
  });
});
