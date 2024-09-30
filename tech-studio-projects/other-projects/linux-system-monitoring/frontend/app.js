document.addEventListener("DOMContentLoaded", function() {
    const hostInput = document.getElementById('hostInput');
    const fetchButton = document.getElementById('fetchButton');
    const metricsContainer = document.getElementById('metricsContainer');

    function fetchMetrics(host) {
        fetch(`/api/monitor?host=${host}`)
            .then(response => response.json())
            .then(data => {
                metricsContainer.innerHTML = ''; // Clear existing metrics
                displayMetrics(data);
            })
            .catch(error => console.error('Error fetching metrics:', error));
    }

    function displayMetrics(data) {
        for (const [key, value] of Object.entries(data)) {
            const metricDiv = document.createElement('div');
            metricDiv.className = 'metric';
            metricDiv.innerHTML = `<strong>${key.replace('_', ' ').toUpperCase()}:</strong> ${value}`;
            metricsContainer.appendChild(metricDiv);
        }
    }

    fetchButton.addEventListener('click', function() {
        const host = hostInput.value;
        fetchMetrics(host);
    });

    // Initial fetch for local metrics
    fetchMetrics('');
});
