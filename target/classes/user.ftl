<div id="content">
    <#if user??>
        User:<br>
        id:${user.id}<br>
        email:${user.email}<br>
        name:${user.name}<br>
        birthday:${user.birthday}<br>
    <#else>
        No user found
    </#if>
</div>