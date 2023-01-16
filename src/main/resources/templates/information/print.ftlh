<#import "../parts/common.ftlh" as c>

<@c.page>

    <h2>${lostItem.getName()} found its owner!</h2>

    <#if lostItem.getFilename()??>
        <img class="img-fluid rounded-start" style="max-width: 400px" src="/img/${lostItem.getFilename()}"/>
    </#if>

    <h3>In quantity: ${lostItem.getQuantity()}</h3>

    <h3>Was lost at: ${lostItem.getPlace().getAddress()} ${lostItem.getPlace().getShortDescription()}
        on coordinates: ${lostItem.getPlace().getLatitude()};${lostItem.getPlace().getLongitude()}</h3>

    <h3>Was lost ${lostItem.getDate()}</h3>


    <br/>
    <br/>

    <h4 class="my-1">${date}</h4>

    <br/>
    <br/>

    <div class="input-group my-1">
        <input type="text" placeholder="Name" class="form-control" name="name"/>
        <input type="text" placeholder="Surname" class="form-control" name="surname"/>
    </div>

    <br/>
    <br/>

    <input class="btn btn-info d-print-none" type="button" value="Print" onclick="print()"/>
</@c.page>