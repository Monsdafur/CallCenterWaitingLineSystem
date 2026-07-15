# Call center waiting line system simulator
## Overview
A simple system built in Java to simulate real life call centers queue to process customer calls with a set of priority

## Priority rules
### The priorities include (from lowest importance to highest importance):
- Order of entry: customers who enter earlier has lower order of entry value are processed first
- Call times: how often a customer use this service, customer who return using the service more often are more likely to be served before other customers
- Customer type: VIP or Regular VIP customers are guaranteed to be served before Regular customers

## Algorithm
The simulator is primarily built around a Heap (Priority Queue).
The heap ensures:
- Efficient insertion of new customers
- Efficient removal of the highest-priority customer
- Automatic reordering whenever the queue changes

## Features
- 📂 Load customer data from a JSON file
- ➕ Add new customers to the waiting queue
- ➖ Remove customers from the queue
- ⚡ Process customer with highest priority
- 📜 Store processed customers in a history list
- 🌳 Organize customers using a Heap Tree
- 📖 View the history of previously processed calls
