
import React from 'react';
import renderer from 'react-test-renderer';
import Heatmap from './Heatmap';

test('matches snapshot', () => {
    const tree = renderer.create(<Heatmap />).toJSON();
    expect(tree).toMatchSnapshot();
});
