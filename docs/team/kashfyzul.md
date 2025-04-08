# Kashfy Zul - Project Portfolio Page

## Overview

Manage instrument rentals faster than a typical mouse/GUI driven app! Keep track of stock, user rental history, and
instrument-related finances in an all-in-one tracking app.

### Summary of Contributions

### Features implemented
 * Listing Features
   * `list stock` - List Instrument Stocks  
     Lists the total, rented and available quantity for all instruments in the database according to their INSTRUMENT_NAME
      Format: `list stock`
      

   * `list filter by:` - Filtered List of Instruments  
  Lists the relevant instruments according to the `FILTER` and `SEARCH_TERM` provided by the user.

   Format: `list filter by: FILTER SEARCH_TERM`
      
   * Possible combinations of `FILTER` and `SEARCH_TERM`
      * `FILTER`: `name`, `SEARCH_TERM`: `INSTRUMENT_NAME`
         * Filters instruments according to the specified `INSTRUMENT_NAME`
      * `FILTER`: `model`, `SEARCH_TERM`: `INSTRUMENT_MODEL`
         * Filters instruments according to the specified `INSTRUMENT_MODEL`
      * `FILTER`: `year`, `SEARCH_TERM`: `INSTRUMENT_YEAR`
         * Filters instruments according to the specified `INSTRUMENT_YEAR`
      * `FILTER`: `reserved` (`SEARCH_TERM` is not required)
         * Filters instruments that `is_rented()`
      * `FILTER`: `available` (`SEARCH_TERM` is not required)
         * Filters instruments that are not `is_rented()`
      * `FILTER`: `overdue` (`SEARCH_TERM` is not required)
         * Filters instruments that `is_overdue()`
      
 
   * `list help` - List All list Features  
     Lists all available `list` functionalities. A brief description for each command is included.

   
 * Reserve Features
   * `reserve INSTRUMENT_NUMBER` - Reserves Instrument (Without Deadline)
   * `reserve INSTRUMENT_NUMBER from: START_DATE to: END_DATE` - Reserves Instrument (With Deadline)
 * `extend` - Extend Reservation
 * `return` - Return Instrument
 
### User Guide
   * Introduction
   * Table of Contents
   * Quick Start
   * Command Summary
   * Features
     * Instrument Commands
     * Utility Commands
     * Saving the Data
     * Loading the Data
   * FAQ

### Utils
* `LowStockChecker` - Checks inventory for instruments with depleting stocks

### Testing
* `ExtendCommandTest` - Testing for `extend`
* `ListCommandTest` - Testing for `list`
* `ReserveCommandTest` - Testing for `reserve`
* `ReturnCommandTest` - Testing for `return`
* `LowStockCheckerTest`- Testing for `LowStockChecker` 
* `TimeCheckerTest` - Testing for `TimeChecker`

[Code contributed](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=kashfyzul&breakdown=true)