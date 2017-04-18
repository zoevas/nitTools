NitosTools Application
==========================
NITOS is is a testbed whose nodes are laying in a six-floor building. NITOS Scheduler is a tool which is responsible
for managing the testbed resources. It is advantageous to make an implementation of the NITOS Scheduler in the Android Platform, since the users, reserving the resources through their smartphone, can have better user experience and faster reservations than using the web browser of the mobile phone.



From the left-hand side an Android device (not necessarily a smartphone, it could be a tablet) with the NitosTools
application already installed communicates with the web service using a special RESTful API. The application sends HTTP requests with GET/PUT/POST/DELETE method headers and receives well formatted JSON responses. REST objects can be exchanged
both ways.

![alt tag](https://github.com/zoevas/nitTools/blob/master/System%20architecture.png)




NitosTools consists of four activities:
• LoginScreenActivity: validates the user.
• MainMenuActivity: displays a list of all the testbed tools.
• NitosSchedulerActivity: creates a tabbed user interface.
• MyReservationsActivity: displays all the user’s reservations and offers the ability
for canceling them.
Apart from the activities, the application consists of eight fragments:
• SchedulerChooserFragment displays the user’s interface for choosing the reserva-
tion’s parameters(slice, date, time, duration) and checking the available resources.
• DatePickerFragment: is fired through the SchedulerChooserFragment for the date
selection.
• TimePickerFragment: is fired through the SchedulerChooserFragment for the time
selection.

• OutdoorTestbedFragmen: displays the outdoor testbed
• IndoorTestbedFragment: displays the indoor testbed
• AvailableResourcesFragment: displays a UI displaying all available nodes. The
user can reserve the resources through this fragment.
• CheckResourcesFragment: a nested fragment to the AvailableNodesFragment, which
displays all the nodes of a specific type in a listview of checkboxes.
• ReserveResourcesFragment: performs the reservation process and in case of a suc-
cessful reservation displays an appropriate message.
There are also two classes performing the requests to the server:
• TestbedHttpClient: This class contains methods for connecting with the server
and making GET and POST requests.
• GlobalData: This class contains all the global data for the application
• Reservation: This class contains information(date and time) for a reservation.
• Constants: Constants class is responsible for all the constants of the application.
