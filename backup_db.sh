#!/bin/bash
clear
/Applications/MySQLWorkbench.app/Contents/MacOS/mysqldump --set-charset -h localhost -u root -proot papers_review > papers_review.mysqldump.sql

ls -llh *.sql
