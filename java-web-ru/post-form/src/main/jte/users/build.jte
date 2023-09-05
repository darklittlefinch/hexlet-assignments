<form action="/users" method="post">
    <div>
        <label>
            Your First name
            <input type="text" name="firstName" />
        </label>
    </div>
    <div>
        <label>
            Your Last name
            <input type="text" name="lastName" />
        </label>
    </div>
    <div>
        <label>
            Your Email
            <input type="email" required name="email" />
        </label>
    </div>
    <div>
        <label>
            Create Password
            <input type="password" required name="password" />
        </label>
    </div>
    <div>
        <label>
            Confirm Password
            <input type="password" required name="passwordConfirmation" />
        </label>
    </div>
    <input type="submit" value="Done" />
</form>
