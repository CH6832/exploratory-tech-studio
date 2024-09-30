#!/bin/bash

# Function to get CPU usage
get_cpu_usage() {
    echo "$(top -bn1 | grep "Cpu(s)" | sed "s/.*, *\([0-9.]*\)%* id.*/\1/" | awk '{print 100 - $1}')"
}

# Function to get memory usage
get_memory_usage() {
    mem_info=$(free -m | awk 'NR==2{printf "%s %s", $3, $2 }')
    echo "$mem_info"
}

# Function to get disk usage
get_disk_usage() {
    df -h / | awk 'NR==2{print $5}'
}

# Function to get network stats
get_network_usage() {
    read in out <<< $(cat /proc/net/dev | grep eth0 | awk '{print $2, $10}')
    echo "$in $out"
}

# Function to get uptime
get_uptime() {
    echo "$(uptime -p)"
}

# Function to get active connections
get_active_connections() {
    echo "$(netstat -an | grep ESTABLISHED | wc -l)"
}

# Main function to collect metrics
collect_metrics() {
    cpu_usage=$(get_cpu_usage)
    memory_usage=$(get_memory_usage)
    disk_usage=$(get_disk_usage)
    network_usage=$(get_network_usage)
    uptime=$(get_uptime)
    active_connections=$(get_active_connections)

    echo "{
        \"cpu_usage\": $cpu_usage,
        \"memory_used\": $(echo $memory_usage | awk '{print $1}'),
        \"memory_total\": $(echo $memory_usage | awk '{print $2}'),
        \"disk_usage\": \"$disk_usage\",
        \"network_in\": $in,
        \"network_out\": $out,
        \"uptime\": \"$uptime\",
        \"active_connections\": $active_connections
    }"
}

collect_metrics > /path/to/monitor_data.json
