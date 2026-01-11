# Create Simulator
set ns [new Simulator]

# Open Trace and NAM files
set ntrace [open lab10.tr w]
$ns trace-all $ntrace
set namfile [open lab10.nam w]
$ns namtrace-all $namfile

# Finish Procedure
proc Finish {} {
    global ns ntrace namfile
    $ns flush-trace
    close $ntrace
    close $namfile

    puts "Number of packets dropped:"
    exec grep -c "^d" lab10.tr

    exec nam lab10.nam &
    exit 0
}

# Create 3 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

# Labels
$n0 label "Source"
$n2 label "Sink"

# Create duplex links
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

# Queue size
$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

# Transport layer (UDP)
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set sink0 [new Agent/Null]
$ns attach-agent $n2 $sink0
$ns connect $udp0 $sink0

# Application layer (CBR)
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 100
$cbr0 set rate_ 1Mb
$cbr0 attach-agent $udp0

# Schedule events
$ns at 0.0 "$cbr0 start"
$ns at 5.0 "Finish"

# Run simulation
$ns run
