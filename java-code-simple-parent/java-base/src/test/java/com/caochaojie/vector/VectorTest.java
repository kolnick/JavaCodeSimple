package com.caochaojie.vector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @Author: caochaojie
 * @Date: 2023-11-18 13:43
 */
public class VectorTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Vector3 {
        public float x;
        public float y;
        public float z;
    }

    public static boolean isWithinRange(Vector3 sourceVector, Vector3 pointBox, Vector3 targetVector, Vector3 targetBox) {
        // 计算两个范围在每个坐标轴上的投影
        float minX1 = targetVector.x - targetBox.x / 2.0f;
        float maxX1 = targetVector.x + targetBox.x / 2.0f;

        float minX2 = sourceVector.x - pointBox.x / 2.0f;
        float maxX2 = sourceVector.x + pointBox.x / 2.0f;

        float minY1 = targetVector.y - targetBox.y / 2.0f;
        float maxY1 = targetVector.y + targetBox.y / 2.0f;

        float minY2 = sourceVector.y - pointBox.y / 2.0f;
        float maxY2 = sourceVector.y + pointBox.y / 2.0f;

        float minZ1 = targetVector.z - targetBox.z / 2.0f;
        float maxZ1 = targetVector.z + targetBox.z / 2.0f;

        float minZ2 = sourceVector.z - pointBox.z / 2.0f;
        float maxZ2 = sourceVector.z + pointBox.z / 2.0f;

        // 检查在 x、y 和 z 轴上是否相交
        boolean xOverlap = maxX1 >= minX2 && maxX2 >= minX1;
        boolean yOverlap = maxY1 >= minY2 && maxY2 >= minY1;
        boolean zOverlap = maxZ1 >= minZ2 && maxZ2 >= minZ1;

        // 如果所有轴都相交，说明点在范围内
        return xOverlap && yOverlap && zOverlap;
    }

    @Test
    public void testIsWithinRange() {
        assertTrue(isWithinRange(new Vector3(0, 0, 0),
                new Vector3(1, 1, 1),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 1)));

    }

    @Test
    public void testNotRange2() {
        assertTrue(isWithinRange(new Vector3(0, 0, 0),
                new Vector3(1, 1, 1),
                new Vector3(2, 2, 2),
                new Vector3(1, 1, 1)));

    }

    @Test
    public void testNotInRange() {
        assertFalse(isWithinRange(new Vector3(0, 0, 0),
                new Vector3(1, 1, 1),
                new Vector3(2, 2, 2),
                new Vector3(1, 1, 1)));
    }
}
