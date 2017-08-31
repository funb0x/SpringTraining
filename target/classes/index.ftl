<div id="header">
    <h2>FreeMarker Spring MVC Hello World</h2>
</div>
<div id="content">
    <table class="datatable">
        <tr>
            <th>number</th>
        </tr>
        <#list model["numbers"] as number>
            <tr>
                <td>${number}</td>
            </tr>
        </#list>
    </table>
</div>