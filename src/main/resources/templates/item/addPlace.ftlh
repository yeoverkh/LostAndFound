<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Add new place</h2>

    <div class="form-group mt-2 p-2">
        <form method="post" action="/places">

            <div class="form-group">
                <input type="text" class="form-control col-sm-3"
                       name="address" placeholder="Address">
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3 ${(latitudeError??)?string('is-invalid', '')}"
                       name="latitude" placeholder="Latitude">
                <#if latitudeError??>
                    <div class="invalid-feedback">
                        ${latitudeError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3 ${(longitudeError??)?string('is-invalid', '')}"
                       name="longitude" placeholder="Longitude">
                <#if longitudeError??>
                    <div class="invalid-feedback">
                        ${longitudeError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="shortDescription"
                       placeholder="Short description">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <div class="form-group">
                <button type="submit" class="btn btn-success">Add</button>
            </div>
        </form>
    </div>
</@c.page>