<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Add new peculiarities</h2>

    <div class="form-group mt-2 p-2">
        <form method="post" action="/peculiarities">

            <div class="form-group">
                <input type="text" class="form-control col-sm-3 ${(nameError??)?string('is-invalid', '')}"
                       name="name" placeholder="Name">
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3 ${(valueError??)?string('is-invalid', '')}"
                       name="value" placeholder="Value">
                <#if valueError??>
                    <div class="invalid-feedback">
                        ${valueError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <select class="form-control col-sm-3" id="unit" name="unit" aria-label="Default select">
                    <option selected>Select unit</option>
                    <#list units as unit>
                        <option value="${unit.getId()}">
                            ${unit.getName()}
                        </option>
                    </#list>
                </select>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <div class="form-group">
                <button type="submit" class="btn btn-success">Add</button>
            </div>
        </form>
    </div>
</@c.page>