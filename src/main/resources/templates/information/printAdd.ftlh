<#import "../parts/common.ftlh" as c>

<@c.page>

    <h2>${lostItem.getName()} was added to lost and found!</h2>

    <#if lostItem.getFilename()??>
        <img class="img-fluid rounded-start" style="max-width: 400px" src="/img/${lostItem.getFilename()}"/>
    </#if>

    <h3>Type: ${lostItem.getType().getName()}</h3>

    <h3>In quantity: ${lostItem.getQuantity()}</h3>

    <h3>Was lost at: ${lostItem.getPlace().getAddress()} ${lostItem.getPlace().getShortDescription()}
        on coordinates: ${lostItem.getPlace().getLatitude()}; ${lostItem.getPlace().getLongitude()}</h3>

    <h3>Was found ${lostItem.getDate()} at ${lostItem.getTime()}</h3>

    <h3>Assessed
        value: ${lostItem.getAssessedValue().getValue()} ${lostItem.getAssessedValue().getCurrency().getName()}</h3>

    <br/>
    <br/>

    <input class="btn btn-info d-print-none" type="button" value="Print" onclick="print()"/>
</@c.page>