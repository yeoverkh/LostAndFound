<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Places</h2>

    <a class="btn btn-success mb-2" href="/places/add" role="button">
        Add new place
    </a>

    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <#if message == "danger">
                Place was deleted!
            <#elseif message == "info">
                Place was edited!
            <#else>
                Place was added!
            </#if>
        </div>
    </#if>

    <#if places?size != 0>
        <table class="table table-striped table-bordered table-hover">

            <thead class="thead-light">
            <tr>
                <th class="align-middle" style="max-width: 0" scope="col">Address</th>
                <th class="align-middle" style="max-width: 50px" scope="col">Latitude</th>
                <th class="align-middle" style="max-width: 50px" scope="col">Longitude</th>
                <th class="align-middle" style="max-width: 50px" scope="col">Short description</th>
                <th style="max-width: 0"></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <#list places as place>
                <tr>
                    <td style="max-width: 70px" class="align-middle">${place.getAddress()}</td>
                    <td style="max-width: 55px" class="align-middle">${place.getLatitude()}</td>
                    <td style="max-width: 65px" class="align-middle">${place.getLongitude()}</td>
                    <td style="max-width: 100px" class="align-middle">${place.getShortDescription()}</td>
                    <td style="max-width: 250px">
                        <form method="post" action="/places/edit/${place.getId()}">
                            <div class="input-group">
                                <input type="text" placeholder="${place.getAddress()}"
                                       class="form-control" name="address"/>
                                <input style="max-width: 80px" type="text" placeholder="${place.getLatitude()}"
                                       class="form-control" name="latitude"/>
                                <input style="max-width: 80px" type="text" placeholder="${place.getLongitude()}"
                                       class="form-control" name="longitude"/>
                            </div>
                            <input type="text" placeholder="${place.getShortDescription()}"
                                   class="form-control" name="shortDescription"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-success align-middle" type="submit" id="button-addon1">
                                Update
                            </button>
                        </form>
                    </td>
                    <td style="max-width: 60px">
                        <div>
                            <form method="post" action="/places/delete/${place.getId()}">
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
        <h6>No places yet</h6>
    </#if>

</@c.page>