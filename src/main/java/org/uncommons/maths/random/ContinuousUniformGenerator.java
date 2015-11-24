// ============================================================================
//   Copyright 2006-2012 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.maths.random;

import org.uncommons.maths.number.NumberGenerator;

import java.util.Random;

/**
 * Continuous, uniformly distributed random sequence.  Generates
 * values in the range {@literal mininum (inclusive) ... maximum (exclusive)}.
 *
 * @author Daniel Dyer
 */
public class ContinuousUniformGenerator implements NumberGenerator<Double> {
    private final Random rng;
    private final double range;
    private final double minimumValue;

    public ContinuousUniformGenerator(double minimumValue,
                                      double maximumValue,
                                      Random rng) {
        this.rng = rng;
        this.minimumValue = minimumValue;
        this.range = maximumValue - minimumValue;
    }


    /**
     * {@inheritDoc}
     */
    public Double nextValue() {
        return rng.nextDouble() * range + minimumValue;
    }
}
