<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Peculiarities</h2>


    <a class="btn btn-success mb-2" href="/peculiarities/add" role="button">
        Add new peculiarities
    </a>

    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <#if message == "danger">
                Peculiarities was deleted!
            <#elseif message == "info">
                Peculiarities was edited!
            <#else>
                Peculiarities was added!
            </#if>
        </div>
    </#if>

    <table class="table table-striped table-bordered table-hover">

        <thead class="thead-light">
        <tr>
            <th class="align-middle" scope="col" style="max-width: 0">Name</th>
            <th class="align-middle" scope="col" style="max-width: 0">Value</th>
            <th class="align-middle" scope="col" style="max-width: 0">Unit</th>
            <th style="max-width: 0"></th>
            <th style="max-width: 0"></th>
            <th style="max-width: 0"></th>
        </tr>
        </thead>

        <tbody>
        <#list peculiarities as peculiarity>
            <tr>
                <td class="align-middle">${peculiarity.getName()}</td>
                <td class="align-middle">${peculiarity.getValue()}</td>
                <td class="align-middle">${peculiarity.getUnit().getName()}</td>
                <td style="max-width: 290px">
                    <form method="post" action="/peculiarities/edit/${peculiarity.getId()}">
                        <div class="input-group">
                            <input type="text" placeholder="${peculiarity.getName()}"
                                   class="form-control" name="name"/>
                            <input type="text" placeholder="${peculiarity.getValue()}"
                                   class="form-control" name="value"/>
                            <select class="form-control" id="unit" name="unit" aria-label="Default select">
                                <option selected="selected" value="-1">Select unit</option>
                                <#list units as unit>
                                    <option value="${unit.getId()}">
                                        ${unit.getName()}
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
                <td style="max-width: 60px">
                    <div>
                        <form method="post" action="/peculiarities/delete/${peculiarity.getId()}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </div>
                </td>
                <td style="max-width: 50px">
                    <div>
                        <form method="get" action="/information/${peculiarity.getId()}/items">
                            <button class="btn btn-info" type="submit">Items</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>



    <h2>Units</h2>

    <a class="btn btn-success mb-2" data-toggle="collapse" href="#addUnit" role="button" aria-expanded="false"
       aria-controls="addUnit">
        Add new unit
    </a>

    <div class="collapse <#if color??>show<#else></#if>" id="addUnit">
        <div class="form-group mt-2 p-2">
            <form method="post" action="/peculiarities/units">

                <div class="form-group">
                    <input type="text" class="form-control col-sm-3" name="name" placeholder="Name" required/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add</button>
                </div>
            </form>
        </div>
    </div>

    <#if messageUnit??>
        <div class="alert alert-${messageUnit}" role="alert">
            <#if messageUnit == "danger">
                Unit was deleted!
            <#elseif messageUnit == "info">
                Unit was edited!
            <#else>
                Unit was added!
            </#if>
        </div>
    </#if>

    <table class="table table-striped table-bordered table-hover" style="max-width: 500px">

        <thead class="thead-light">
        <tr>
            <th class="align-middle" scope="col" style="max-width: 0">Name</th>
            <th style="max-width: 0"></th>
            <th style="max-width: 0"></th>
        </tr>
        </thead>

        <tbody>
        <#list units as unit>
            <tr>
                <td class="align-middle">${unit.getName()}</td>
                <td style="max-width: 100px">
                    <form method="post" action="/peculiarities/units/edit/${unit.getId()}">
                        <div class="input-group">
                            <input type="text" placeholder="${unit.getName()}"
                                   class="form-control" name="name"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-success align-middle" type="submit" id="button-addon1">
                                Update
                            </button>
                        </div>
                    </form>
                </td>
                <td style="max-width: 40px">
                    <div>
                        <form method="post" action="/peculiarities/units/delete/${unit.getId()}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>

    </table>

</@c.page>