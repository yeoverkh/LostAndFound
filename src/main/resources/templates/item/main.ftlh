<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Lost items</h2>

    <a class="btn btn-success mb-2" href="/main/add">Add new item</a>

    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <#if message == "danger">
                Item was deleted!
            <#elseif message == "info">
                Item was edited!
            <#else>
                Item was added!
            </#if>
        </div>
    </#if>


    <div class="row justify-content-start mb-2 mx-1">
        <div>
            <h5>Sort by</h5>

            <form method="post" action="/main/sort">
                <select class="form-control mb-2" name="value">
                    <option selected="selected">Choose sort value</option>
                    <option value="assessedValue">Assessed value</option>
                    <option value="date">Date</option>
                    <option value="quantity">Quantity</option>
                </select>

                <select class="form-control mb-2" name="sortType">
                    <option selected="selected">Choose sort type</option>
                    <option value="ascending">Ascending</option>
                    <option value="descending">Descending</option>
                </select>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button class="btn btn-primary" type="submit">Sort</button>
            </form>
        </div>
        <div class="mx-5">
            <h5>Search</h5>
            <form method="post" action="/main/search">
                <input class="form-control mb-2" type="text" name="search" placeholder="Search by name"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>


    <div class="card-columns">

        <#list items as item>

            <div class="card my-3">

                <div class="m-3">
                    <#if item.filename??>
                        <img class="card-img-top" src="/img/${item.filename}">
                    </#if>
                </div>

                <div class="row justify-content-center m-3">
                    <h5>${item.getName()}</h5>
                </div>

                <div class="m-3">
                    <h6>Quantity: ${item.getQuantity()}</h6>
                </div>

                <div class="m-3">
                    <h6>Peculiarities: </h6>
                    <#list item.peculiarities as peculiarity>
                        <div>${peculiarity.name} ${peculiarity.value} ${peculiarity.unit.name}</div>
                    <#else>
                        <div>No peculiarities yet</div>
                    </#list>

                    <div><a href="/information/${item.id}" class="btn btn-info">Edit peculiarities</a></div>
                </div>

                <div class="m-3">
                    <h6>Place: </h6>
                    <div>${item.place.address}</div>
                    <div>${item.place.shortDescription}</div>
                    <div>Coordinates: ${item.place.latitude}; ${item.place.longitude}</div>
                </div>

                <div class="m-3">
                    <h6>Assessed value: </h6>
                    <div>${item.assessedValue.value} ${item.assessedValue.currency.name}</div>
                </div>

                <div class="m-3">
                    <h6>Date and time: </h6>
                    <div>${item.date} at ${item.time}</div>
                </div>

                <div class="card-footer text-muted">
                    <div class="row justify-content-between">
                        <div class="col my-2"><a href="/main/type/${item.type.id}">${item.type.name}</a></div>

                        <div class="btn-group col">
                            <a href="/main/edit/${item.getId()}">
                                <button style="max-height: 40px" class="btn btn-success">Edit</button>
                            </a>

                            <form method="post" action="/main/delete/${item.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button style="max-height: 40px" class="btn btn-danger" type="submit">Delete
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <h6>No items yet</h6>
        </#list>
    </div>

</@c.page>