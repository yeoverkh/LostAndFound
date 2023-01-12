<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Information</h2>

    <h4>${peculiarities.getName()} ${peculiarities.getValue()} ${peculiarities.getUnit().getName()}</h4>

    <table class="table table-striped table-bordered table-hover">

        <thead class="thead-light">
        <tr>
            <th class="align-middle" scope="col" style="max-width: 0">Name</th>
            <th class="align-middle" scope="col" style="max-width: 60px">Quantity</th>
            <th class="align-middle" scope="col" style="max-width: 0">Place</th>
            <th class="align-middle" scope="col" style="max-width: 0">Type</th>
            <th class="align-middle" scope="col" style="max-width: 0">Date and time</th>
            <th class="align-middle" scope="col" style="max-width: 0">Assessed value</th>
            <th style="max-width: 0"></th>
        </tr>
        </thead>

        <tbody>
        <#list items as item>
            <tr>
                <td class="align-middle">${item.getName()}</td>
                <td class="align-middle">${item.getQuantity()}</td>
                <td style="max-width: 200px" class="align-middle">${item.getPlace().getAddress()} ${item.getPlace().getShortDescription()}</td>
                <td class="align-middle">${item.getType().getName()}</td>
                <td style="max-width: 80px" class="align-middle">${item.getDate()} ${item.getTime()}</td>
                <td class="align-middle">${item.getAssessedValue().getValue()} ${item.getAssessedValue().getCurrency().getName()}</td>
                <td style="max-width: 60px" class="align-middle">
                    <div>
                        <form method="post" action="/information/${peculiarities.getId()}/items/remove/${item.getId()}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">

                            <button class="btn btn-danger" type="submit">Remove</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>


    <table class="table table-striped table-bordered table-hover">

        <thead class="thead-light">
        <tr>
            <th class="align-middle" scope="col" style="max-width: 0">Name</th>
            <th class="align-middle" scope="col" style="max-width: 60px">Quantity</th>
            <th class="align-middle" scope="col" style="max-width: 0">Place</th>
            <th class="align-middle" scope="col" style="max-width: 0">Type</th>
            <th class="align-middle" scope="col" style="max-width: 0">Date and time</th>
            <th class="align-middle" scope="col" style="max-width: 0">Assessed value</th>
            <th style="max-width: 0"></th>
        </tr>
        </thead>

        <tbody>
        <#list allLostItems as item>
            <tr>
                <td class="align-middle">${item.getName()}</td>
                <td class="align-middle">${item.getQuantity()}</td>
                <td style="max-width: 200px" class="align-middle">${item.getPlace().getAddress()} ${item.getPlace().getShortDescription()}</td>
                <td class="align-middle">${item.getType().getName()}</td>
                <td style="max-width: 80px" class="align-middle">${item.getDate()} ${item.getTime()}</td>
                <td class="align-middle">${item.getAssessedValue().getValue()} ${item.getAssessedValue().getCurrency().getName()}</td>
                <td style="max-width: 60px" class="align-middle">
                    <div>
                        <form method="post" action="/information/${peculiarities.getId()}/items/add/${item.getId()}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">

                            <button class="btn btn-success" type="submit">Add</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>