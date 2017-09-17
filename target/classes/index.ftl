<div id="header">
    <h2>Spring training home</h2>
</div>
<div id="content">
-----------------------------------------------------------------------------<br>
    for getTicketPrice mapping<br>
    <form action="get_price" method="post" enctype="multipart/form-data">
        Event name: <input type="text" name="eventName"><br>
        Auditorium name: <input type="text" name="auditoriumName"><br>
        Date: <input type="date" name="dateTime"><br>
        Seats: <input type="text" name="seats"><br>
        User Name: <input type="text" name="name"><br>
        <input type="submit" value="Submit">
    </form>
-----------------------------------------------------------------------------<br>
    for book ticket mapping<br>
    <form action="book_ticket" method="post" enctype="multipart/form-data">
        Event name: <input type="text" name="event.name"><br>
        Date: <input type="date" name="date"><br>
        Seats: <input type="text" name="seats"><br>
        Auditorium name: <input type="text" name="event.auditorium.name"><br>
        User email: <input type="text" name="email"><br>
        <input type="submit" value="Submit">
    </form>
-----------------------------------------------------------------------------<br>
    file upload<br>
    <form action="user_upload" method="post" enctype="multipart/form-data">
        File with users: <input type="file" name="users" accept="application/json" /><br/>
        <input type="submit" value="Submit">
    </form>
-----------------------------------------------------------------------------<br>
    to check view rendering<br>
    http://localhost:8080/user/JohnDoe@aol.com<br>
    http://localhost:8080/user/FooBar@aol.com<br>
-----------------------------------------------------------------------------<br>
    pdf<br>
    http://localhost:8080/user/tickets<br>
-----------------------------------------------------------------------------<br>
    json of all users
    http://localhost:8080/user
    for getTicketPrice mapping<br>
-----------------------------------------------------------------------------<br>
    register a user
    <form action="register_user" method="post" enctype="multipart/form-data">
        Email: <input type="text" name="email"><br>
        Name: <input type="text" name="name"><br>
        Date: <input type="date" name="birthday"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Submit">
    </form>
-----------------------------------------------------------------------------<br>
    register a manager
    <form action="register_manager" method="post" enctype="multipart/form-data">
        Email: <input type="text" name="email"><br>
        Name: <input type="text" name="name"><br>
        Date: <input type="date" name="birthday"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Submit">
    </form>
-----------------------------------------------------------------------------<br>
</div>