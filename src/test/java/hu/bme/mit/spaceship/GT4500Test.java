package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTs;
  private TorpedoStore secondaryTs;

  @BeforeEach
  public void init(){
    this.primaryTs= mock(TorpedoStore.class);
    this.secondaryTs= mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTs, secondaryTs);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(1)).fire(1);
    verify(secondaryTs, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Success_First_empty_Second_fire(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(true);
    when(secondaryTs.isEmpty()).thenReturn(false);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(0)).fire(1);
    verify(secondaryTs, times(2)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Success_First_fire_Second_empty(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(secondaryTs.isEmpty()).thenReturn(true);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(2)).fire(1);
    verify(secondaryTs, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Success_First_fire_First_fail(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(secondaryTs.isEmpty()).thenReturn(true);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    when(primaryTs.isEmpty()).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(1)).fire(1);
    verify(secondaryTs, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Success_Both(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(secondaryTs.isEmpty()).thenReturn(false);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(1)).fire(1);
    verify(secondaryTs, times(1)).fire(1);

  }
  @Test
  public void fireTorpedo_Single_Fail_Both(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(true);
    when(secondaryTs.isEmpty()).thenReturn(true);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(primaryTs, times(0)).fire(1);
    verify(secondaryTs, times(0)).fire(1);

  }
  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(secondaryTs.isEmpty()).thenReturn(false);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(primaryTs, times(1)).fire(1);
    verify(secondaryTs, times(1)).fire(1);


  }
  @Test
  public void fireTorpedo_All_Empty(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(true);
    when(secondaryTs.isEmpty()).thenReturn(true);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.ALL);
    verify(primaryTs, times(0)).fire(1);
    verify(secondaryTs, times(0)).fire(1);
  }
  public void fireTorpedo_Wrong(){
    // Arrange

    when(primaryTs.isEmpty()).thenReturn(false);
    when(secondaryTs.isEmpty()).thenReturn(false);
    when(primaryTs.fire(1)).thenReturn(true);
    when(secondaryTs.fire(1)).thenReturn(true);
    // Act
    //ship.fireTorpedo();
    verify(primaryTs, times(0)).fire(1);
    verify(secondaryTs, times(0)).fire(1);
  }
}
