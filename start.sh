#! /bin/bash
set -m

function end_processes()
{
    echo "Terminating programs"
    kill 0
}

(trap end_processes SIGINT; npm run watch & mvn spring-boot:run)