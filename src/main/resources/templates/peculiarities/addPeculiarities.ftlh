<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Add new peculiarities</h2>

    <#if isEmpty??>
        <div class="alert alert-danger" role="alert">
            You can not add new peculiarities without unit.
        </div>
    </#if>

    <div class="form-group mt-2 p-2">
        <form method="post" action="/peculiarities">

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="name" placeholder="Name"
                       value="<#if name??>${name}</#if>" required/>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="value" placeholder="Value"
                       value="<#if value??>${value}</#if>" required/>
            </div>

            <div class="form-group">
                <select class="form-control col-sm-3" id="unit" name="unit" aria-label="Default select" required>
                    <option selected="selected" value="-1">Select unit</option>
                    <#list units as unit>
                        <option value="${unit.getId()}">
                            ${unit.getName()}
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
</@c.page>