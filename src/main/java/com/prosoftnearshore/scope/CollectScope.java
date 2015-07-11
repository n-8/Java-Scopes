/*
Copyright 2015 Prosoft, LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.prosoftnearshore.scope;

public class CollectScope implements Scope {

	public static CollectScope getNew() {
		return new CollectScope();
	}

	public <T extends AutoCloseable> T add(T resource) {
		return this.wrapper.add(resource);
	}

	@Override
	public void close() throws RuntimeException {
		this.wrapper.close();
	}

	public WrapperScope release() {
		WrapperScope r = WrapperScope.getNew();
		this.wrapper.swap(r);
		return r;
	}

	//
	// Private Members
	//

	private final WrapperScope wrapper = WrapperScope.getNew();

	private CollectScope() {
		// hide constructor from API
	}

}
