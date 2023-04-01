<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Information</h2>

    <h4>${item.getName()}</h4>

    <#if peculiarities?size != 0>
        <table class="table table-striped table-bordered table-hover">

            <thead class="thead-light">
            <tr>
                <th class="align-middle" scope="col" style="max-width: 0">Name</th>
                <th class="align-middle" scope="col" style="max-width: 0">Value</th>
                <th class="align-middle" scope="col" style="max-width: 0">Unit</th>
                <th style="max-width: 0"></th>
            </tr>
            </thead>

            <tbody>
            <#list peculiarities as peculiarity>
                <tr>
                    <td class="align-middle">${peculiarity.getName()}</td>
                    <td class="align-middle">${peculiarity.getValue()}</td>
                    <td class="align-middle">${peculiarity.getUnit().getName()}</td>
                    <td style="max-width: 60px">
                        <div>
                            <form method="post" action="/information/${item.getId()}/remove/${peculiarity.getId()}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                                <button class="btn btn-danger" type="submit">Remove</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <h6>No peculiarities that corresponds to this item yet</h6>
    </#if>

    <#if allPeculiarities?size != 0>
        <table class="table table-striped table-bordered table-hover">

            <thead class="thead-light">
            <tr>
                <th class="align-middle" scope="col" style="max-width: 0">Name</th>
                <th class="align-middle" scope="col" style="max-width: 0">Value</th>
                <th class="align-middle" scope="col" style="max-width: 0">Unit</th>
                <th style="max-width: 0"></th>
            </tr>
            </thead>

            <tbody>
            <#list allPeculiarities as peculiarity>
                <tr>
                    <td class="align-middle">${peculiarity.getName()}</td>
                    <td class="align-middle">${peculiarity.getValue()}</td>
                    <td class="align-middle">${peculiarity.getUnit().getName()}</td>
                    <td style="max-width: 60px">
                        <div>
                            <form method="post" action="/information/${item.getId()}/add/${peculiarity.getId()}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                                <button class="btn btn-success" type="submit">Add</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <h6>No peculiarities yet</h6>
    </#if>

</@c.page>