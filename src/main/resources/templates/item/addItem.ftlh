<#import "../parts/common.ftlh" as c>

<@c.page>

    <h3>Add new item</h3>

    <#if message??>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>

    <div class="form-group mt-2 p-2">
        <form method="post" action="/main" enctype="multipart/form-data">

            <input type="text" class="form-control col-sm-3 mb-2" required name="name" placeholder="Name"/>

            <input type="text" class="form-control col-sm-3 mb-2" required name="quantity" placeholder="Quantity"/>

            <select class="form-control col-sm-3 mb-2" name="place" required>
                <option selected="selected" value="-1">Select place</option>
                <#list places as selectablePlace>
                    <option value="${selectablePlace.getId()}">
                        ${selectablePlace.getAddress()}
                        ${selectablePlace.getShortDescription()}
                    </option>
                </#list>
            </select>

            <select class="form-control col-sm-3 mb-2" name="type" required>
                <option selected value="-1">Select type</option>
                <#list types as selectableType>
                    <option value="${selectableType.getId()}">
                        ${selectableType.getName()}
                    </option>
                </#list>
            </select>

            <select class="form-control col-sm-3 mb-2" name="assessedValue" required>
                <option selected="selected" value="-1">Select assessed value</option>
                <#list assessedValues as value>
                    <option value="${value.getId()}">
                        ${value.getValue()} ${value.getCurrency().getName()}
                    </option>
                </#list>
            </select>


            <input placeholder="Select date" type="date" id="example" name="date"
                   class="form-control md-form input-with-post-icon datepicker col-sm-3 mb-2" required/>

            <input type="time" name="stringTime" class="form-control cs-form col-sm-3 mb-2" value="15:11" required/>


            <div class="form-group">
                <div class="custom-file col-sm-3 mb-2">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>

            <br/>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" class="btn btn-success">Add</button>
        </form>
    </div>
</@c.page>