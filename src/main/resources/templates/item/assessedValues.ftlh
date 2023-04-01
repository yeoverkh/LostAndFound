<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Assessed values</h2>

    <a class="btn btn-success mb-2" data-toggle="collapse" href="#addValue" role="button" aria-expanded="false"
       aria-controls="addValue">
        Add new assessed value
    </a>

    <div class="collapse" id="addValue">
        <div class="form-group mt-2 p-2">
            <form method="post" action="/values">

                <div class="form-group">
                    <input type="text" class="form-control col-sm-3" name="value" placeholder="Value" required="required"/>
                </div>

                <div class="form-group">
                    <select class="form-control col-sm-3" id="currency" name="currency" aria-label="Default select">
                        <option selected="selected" value="-1">Select currency</option>
                        <#list currencies as currency>
                            <option value="${currency.getId()}">
                                ${currency.getName()}
                            </option>
                        </#list>
                    </select>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add</button>
                </div>
            </form>
        </div>
    </div>

    <#if isEmpty??>
        <div class="alert alert-danger" role="alert">
            You can not add new assessed value without currency.
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <#if message == "danger">
                Assessed value was deleted!
            <#elseif message == "info">
                Assessed value was edited!
            <#else>
                Assessed value was added!
            </#if>
        </div>
    </#if>

    <#if values?size != 0>
        <table class="table table-striped table-bordered table-hover" style="max-width: 900px">

            <thead class="thead-light">
            <tr>
                <th class="align-middle" scope="col" style="max-width: 0">Value</th>
                <th class="align-middle" scope="col" style="max-width: 0">Currency</th>
                <th style="max-width: 0"></th>
                <th style="max-width: 0"></th>
            </tr>
            </thead>

            <tbody>
            <#list values as value>
                <tr>
                    <td class="align-middle">${value.getValue()}</td>
                    <td class="align-middle">${value.getCurrency().getName()}</td>
                    <td style="max-width: 140px">
                        <form method="post" action="/values/edit/${value.getId()}">
                            <div class="input-group">
                                <input type="text" placeholder="${value.getValue()}"
                                       style="max-width: 100px" class="form-control" name="value"/>
                                <select class="form-control" id="currency" name="currency" aria-label="Default select">
                                    <option selected="selected" value="-1">Select currency</option>
                                    <#list currencies as currency>
                                        <option value="${currency.getId()}">
                                            ${currency.getName()}
                                        </option>
                                    </#list>
                                </select>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-success align-middle" type="submit" id="button-addon1">
                                    Update
                                </button>
                            </div>
                        </form>
                    </td>
                    <td style="max-width: 35px">
                        <div>
                            <form method="post" action="/values/delete/${value.getId()}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>

            </#list>
            </tbody>

        </table>
    <#else>
        <h6>No assessed values yet</h6>
    </#if>




    <h2>Currencies</h2>

    <a class="btn btn-success mb-2" data-toggle="collapse" href="#addCurrency" role="button" aria-expanded="false"
       aria-controls="addValue">
        Add new currency
    </a>

    <div class="collapse" id="addCurrency">
        <div class="form-group mt-2 p-2">
            <form method="post" action="/values/currencies">

                <div class="form-group">
                    <input type="text" class="form-control col-sm-3" name="name" placeholder="Name" required="required"/>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add</button>
                </div>
            </form>
        </div>
    </div>


    <#if messageCurrency??>
        <div class="alert alert-${messageCurrency}" role="alert">
            <#if messageCurrency == "danger">
                Currency was deleted!
            <#elseif messageCurrency == "info">
                Currency was edited!
            <#else>
                Currency was added!
            </#if>
        </div>
    </#if>

    <#if currencies?size != 0>
        <table class="table table-striped table-bordered table-hover" style="max-width: 440px">

            <thead class="thead-light">
            <tr>
                <th class="align-middle" scope="col" style="max-width: 0">Name</th>
                <th style="max-width: 0"></th>
                <th style="max-width: 0"></th>
            </tr>
            </thead>

            <tbody>
            <#list currencies as currency>
                <tr>
                    <td class="align-middle">${currency.getName()}</td>
                    <td style="max-width: 100px">
                        <form method="post" action="/values/currencies/edit/${currency.getId()}">
                            <div class="input-group">
                                <input type="text" placeholder="${currency.getName()}" class="form-control"
                                       name="name"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-success align-middle" type="submit" id="button-addon1">
                                    Update
                                </button>
                            </div>
                        </form>
                    </td>
                    <td style="max-width: 50px">
                        <div>
                            <form method="post" action="/values/currencies/delete/${currency.getId()}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>

            </#list>
            </tbody>

        </table>

    <#else>
        <h6>No currencies yet</h6>
    </#if>
</@c.page>