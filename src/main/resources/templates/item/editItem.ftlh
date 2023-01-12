<#import "../parts/common.ftlh" as c>

<@c.page>

    <h3>Edit item</h3>

    <div class="form-group mt-2 p-2" >
        <form method="post" action="/main/edited/${item.getId()}" enctype="multipart/form-data">

            <input type="text" class="form-control col-sm-3 mb-2"
                   name="name" placeholder="${item.getName()}">

            <input type="text" class="form-control col-sm-3 mb-2"
                   name="quantity" placeholder="${item.getQuantity()}">

            <select class="form-control col-sm-3 mb-2" name="place">
                <option value="${item.getPlace().getId()}">
                    ${item.getPlace().getAddress()}
                    ${item.getPlace().getShortDescription()}
                </option>
                <#list places as selectablePlace>
                    <option value="${selectablePlace.getId()}">
                        ${selectablePlace.getAddress()}
                        ${selectablePlace.getShortDescription()}
                    </option>
                </#list>
            </select>

            <select class="form-control col-sm-3 mb-2" name="type">
                <option value="${item.getType().getId()}">${item.getType().getName()}</option>
                <#list types as selectableType>
                    <option value="${selectableType.getId()}">
                        ${selectableType.getName()}
                    </option>
                </#list>
            </select>

            <select class="form-control col-sm-3 mb-2" name="assessedValue">
                <option value="${item.getAssessedValue().getId()}">
                    ${item.getAssessedValue().getValue()} ${item.getAssessedValue().getCurrency().getName()}
                </option>
                <#list assessedValues as value>
                    <option value="${value.getId()}">
                        ${value.getValue()} ${value.getCurrency().getName()}
                    </option>
                </#list>
            </select>


            <input placeholder="Select date" type="date" id="example" name="date" value="${item.getDate()}"
                   class="form-control md-form input-with-post-icon datepicker col-sm-3 mb-2">

            <input type="time" name="stringTime" class="form-control cs-form col-sm-3 mb-2" value="${item.getTime()}"/>

            <div class="form-group">
                <div class="custom-file col-sm-3 mb-2">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <button type="submit" class="btn btn-success">Edit</button>
        </form>
    </div>
</@c.page>