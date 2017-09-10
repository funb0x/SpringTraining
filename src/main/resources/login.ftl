<title>Login Page</title>
<h3>Login with Username and Password</h3>
<#if (RequestParameters.error)??>Invalid username or password</#if>
<form name='f' action='login' method='POST'>
    <table>
        <tr><td>Email:</td><td>
            <input type='text' name='username' value=''></td></tr>
        <tr><td>Password:</td>
            <td>
                <input type='password' name='password'/>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form>

